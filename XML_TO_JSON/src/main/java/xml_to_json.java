import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class xml_to_json {


    String nombreArch = "car_sales";
    static List<sales> listaVentasAutos = new ArrayList<sales>();

    public static void main(String[] args) {
        
        readXML(listaVentasAutos);
        convertToJSON(listaVentasAutos);

    }

    public static void convertToJSON(List<sales> listaVentasAutos) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i< listaVentasAutos.size(); i++){
            JSONObject jsonObj1 = new JSONObject();
            jsonObj1.put("id", listaVentasAutos.get(i).getId());
            jsonObj1.put("first_name", listaVentasAutos.get(i).getFirst_name());
            jsonObj1.put("last_name", listaVentasAutos.get(i).getLast_name());
            jsonObj1.put("car", listaVentasAutos.get(i).getCar());
            jsonObj1.put("price", listaVentasAutos.get(i).getPrice());
            jsonObj1.put("state", listaVentasAutos.get(i).getState());

        }

        jsonObj.put("car_sales", jsonArr);

        System.out.println(jsonObj);

    }

    public static void readXML(List<sales> listaVentasAutos) {

        try {

            File file = new File("car_sales.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            NodeList listaN = doc.getElementsByTagName("CAR_SALES");

            for (int i = 0; i<listaN.getLength(); i++){

                Node nod = listaN.item(i);

                if (nod.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element) nod;

                    listaVentasAutos.add(
                            new sales(
                                    elem.getElementsByTagName("ID").item(0).getTextContent(),
                                    elem.getElementsByTagName("FIRST_NAME").item(0).getTextContent(),
                                    elem.getElementsByTagName("LAST_NAME").item(0).getTextContent(),
                                    elem.getElementsByTagName("CAR").item(0).getTextContent(),
                                    elem.getElementsByTagName("PRICE").item(0).getTextContent(),
                                    elem.getElementsByTagName("STATE").item(0).getTextContent()
                            )
                    );
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }


}
