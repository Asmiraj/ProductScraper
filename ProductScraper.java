import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductScraper {

    public static void main(String[] args) {
        String url = "https://www.example.com/products";  // Replace with the actual URL
        String filePath = "products.csv";
        
        try {
            Document doc = Jsoup.connect(url).get();  // Fetch the HTML document from the URL

            // Open a CSV file for writing the data
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("Product Name,Price,Rating\n");  // Write CSV header

            // Parse product data
            Elements products = doc.select(".product");  // Replace with actual CSS selector for product containers

            for (Element product : products) {
                String name = product.select(".product-name").text();  // Replace with actual CSS selector for product name
                String price = product.select(".product-price").text();  // Replace with actual CSS selector for price
                String rating = product.select(".product-rating").text();  // Replace with actual CSS selector for rating

                // Write each product data to the CSV file
                writer.write(String.format("\"%s\",\"%s\",\"%s\"\n", name, price, rating));
            }

            // Close the CSV file
            writer.close();
            System.out.println("Product data has been successfully written to " + filePath);

        } catch (IOException e) {
            System.out.println("An error occurred during web scraping: " + e.getMessage());
        }
    }
}
