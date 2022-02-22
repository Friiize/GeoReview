package com.example.georeview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class DBHandler extends AsyncTask<String, Void, String> {
    String NAMESPACE = "http://192.168.1.15:9999/";
    String URL = NAMESPACE + "GeoReviewEnds/src/requests.php?wsdl";
    String SOAP_ACTION = "";
    String METHOD_NAME;
    String PARAMETER_NAME = "name";
    String result = null;
    UserLogModal userLogModal;
    ArrayList<ItemModal> itemModalArrayList;

    public void setMETHOD_NAME(String METHOD_NAME) {
        this.METHOD_NAME = METHOD_NAME;
    }

    public void setUserLogModal(UserLogModal userLogModal) {
        this.userLogModal = userLogModal;
    }

    public void setItemModalArrayList(ArrayList<ItemModal> itemModalArrayList) { this.itemModalArrayList = itemModalArrayList; }

    @Override
    protected void onPostExecute(String s) {
        if (METHOD_NAME.equals("login")) userLogModal.username = s; //Ca fonctionne comme ça ok !
        if (METHOD_NAME.equals("reviews")) userLogModal.username = s; //Ca fonctionne comme ça ok !
    }

    @Override
    protected String doInBackground(String... params) {
        SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
        PropertyInfo propertyInfo = new PropertyInfo();
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        propertyInfo.setName(PARAMETER_NAME);
        propertyInfo.setValue(params);
        propertyInfo.setType(String.class);
        soapObject.addProperty(propertyInfo);
        envelope.setOutputSoapObject(soapObject);

        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapObject object = (SoapObject) envelope.getResponse();
            if (METHOD_NAME.equals("get_objs_files"))
                putInItemModalArrayList(object);
            result = object.toString();
        } catch (XmlPullParserException | IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void putInItemModalArrayList(SoapObject object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object.getProperty(0).toString());
        JSONArray jsonArray = jsonObject.getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject singleObject = (JSONObject) jsonArray.getJSONObject(i);
            ItemModal itemModal = new ItemModal(
                    singleObject.getString("id"),
                    singleObject.getString("obj_name"),
                    singleObject.getString("image_path"),
                    singleObject.getString("review"),
                    singleObject.getString("geoloc")
            );
            itemModalArrayList.add(itemModal);
        }
    }
}
