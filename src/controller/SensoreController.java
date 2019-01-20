package controller;

import dao.DatoDao;
import dao.Interface.DatoDaoInterface;
import dao.Interface.SensoreDaoInterface;
import dao.SensoreDao;
import model.Sensore;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SensoreController {

    ArrayList<Sensore> listsensor = new ArrayList<>();
    DatoDaoInterface datoDaoInterface = new DatoDao();
    SensoreDaoInterface sensoreDaoInterface = new SensoreDao();
    ArrayList<Sensore> listdati = new ArrayList<>();
    private Integer size=1;
    public  Integer ct=0;
    public  Integer cu=0;

    public void GenerateVariable()
    {
        listdati.clear();

        if(listsensor.size()!=size)
        try {
            listsensor=sensoreDaoInterface.LoadSensorData();
            size=listsensor.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Random random=new Random();
        boolean stato;
        int valore;

        for(int i=0; i<listsensor.size();i++)
        {
            ct=0;
            cu=0;
            stato=random.nextBoolean();

            Date dnow= new Date();
            SimpleDateFormat ft =new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
            String data=ft.format(dnow);
            try {
                dnow=ft.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(listsensor.get(i).getTipo().equals("temperatura"))           //controllo se il sensore acquisisce un valore relativo alla temperatura
            {
                int min=-20;
                int max=40;
                int med=((max-min) + 1);
                valore=random.nextInt(med) + min;

                if(valore>listsensor.get(i).getMassimale()){
                    ct++;
                    listdati.add(new Sensore(listsensor.get(i).getCodices(), stato, 1, listsensor.get(i).getFrequenza()+ct,valore, listsensor.get(i).getTipo(), dnow));
                }else{
                    ct=0;
                    listdati.add(new Sensore(listsensor.get(i).getCodices(), stato, 1, ct,valore, listsensor.get(i).getTipo(), dnow));
                }
            }else{

                int min = 0;
                int max = 100;
                int med = ((max - min) + 1);
                valore = random.nextInt(med) + min;

                if(valore>listsensor.get(i).getMassimale()){
                    cu++;
                    listdati.add(new Sensore(listsensor.get(i).getCodices(), stato, 1, listsensor.get(i).getFrequenza()+cu,valore, listsensor.get(i).getTipo(), dnow));
                }else{
                    cu=0;

                    listdati.add(new Sensore(listsensor.get(i).getCodices(), stato, 1, cu,valore, listsensor.get(i).getTipo(), dnow));
                }
            }

            if(listdati.get(i).getFrequenza()>=3)
            {
                JavaFXController.t=3;
                JavaFXController.timeline2.play();
            }else{
                JavaFXController.t=0;
            }
        }
        listsensor.clear();
    }

    public void InvioDati(){
                try {
            datoDaoInterface.SendData(listdati);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void InvioDatiFreq(){            //metodo per effettuare un invio frequente dei dati ogni 30sec
        if(JavaFXController.t!=0)
        {
            try {
                System.out.print("SONO NELLA SECONDA TIMELINE");
                datoDaoInterface.SendDataFreq(listdati);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JavaFXController.timeline2.stop();
            JavaFXController.t=0;
        }
    }
}

