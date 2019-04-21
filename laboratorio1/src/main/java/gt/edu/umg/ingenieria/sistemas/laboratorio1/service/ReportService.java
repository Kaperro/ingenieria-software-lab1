package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Service
public class ReportService {
    public String generateReport(List<Client> clientList, ServletContext servletContext) {

        String servletContextRealPath = servletContext.getRealPath("/");
        String directory = "var/wwww";

        File reportsDirectory = new File(servletContextRealPath + directory);
        List<File> filesInReportsDirectory = new ArrayList<>();
        if (!reportsDirectory.exists()){
            boolean mkdirs = reportsDirectory.mkdirs();
        } else {
            try (Stream<Path> paths = Files.walk(Paths.get(servletContextRealPath + directory))) {
                filesInReportsDirectory = paths
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = "/Clientes_1.html";
        if (!filesInReportsDirectory.isEmpty()){
            fileName = "/Clientes_" + (filesInReportsDirectory.size() + 1) + ".html";
        }
        fileName = directory + fileName;
        String filePath = servletContextRealPath + fileName;
        fileName = "/" + fileName;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table id = \"clientReport\" border = \"1\">\n");
        stringBuilder.append("<tr>\n");
        for (Field field : Client.class.getDeclaredFields()) {
            stringBuilder.append("<th>");
            stringBuilder.append(field.getName());
            stringBuilder.append("</th>");
        }
        stringBuilder.append("</tr>\n");

        for (Client client: clientList) {
            stringBuilder.append("<tr>\n");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getId());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getFirstName());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getLastName());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getNit());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getPhone());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getAddress());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(client.getDate());
            stringBuilder.append("</td>");
            stringBuilder.append("</tr>\n");
        }

        stringBuilder.append("</table>\n");

        Writer writer;
        try {
            writer = new FileWriter(file);
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        return "El reporte " + fileName + " ha sido generado.";
    }

}
