package reporteventas;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReporteVentas {

    public static void main(String[] args) {
         try {
            
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("C:/Users/Hp/Downloads/car_sales.json"));

            
            Map<String, Double> PrecioPromedio = new HashMap<>();

            for (Object obj : jsonArray) {
                JSONObject carro = (JSONObject) obj;
                String marca = (String) carro.get("car");

                
                String precioStr = (String) carro.get("price");
                precioStr = precioStr.replaceAll("[^0-9.]", ""); // Para eliminar caracteres no numéricos excepto el punto
                double precio = Double.parseDouble(precioStr);

                PrecioPromedio.put(marca, PrecioPromedio.getOrDefault(marca, 0.0) + precio);
            }

            
            for (String marca : PrecioPromedio.keySet()) {
                double precioProm = PrecioPromedio.get(marca) / jsonArray.size();
                System.out.println("Marca: " + marca + "\n Precio Promedio: " + precioProm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

