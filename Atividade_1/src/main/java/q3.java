import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
public class q3 {

    public static void main(String[] args) throws IOException{
        List<Livro> livroObjs = new ArrayList();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("Livros.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CSVReader csvReader = new CSVReaderBuilder(reader).build();

        List<String[]> livros = null;
        try {
            csvReader.readNext();
            livros = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }


        for (String[] livro : livros){
            livroObjs.add(new Livro(livro[0],livro[1],livro[2],livro[3],livro[4]));
        }
        System.out.print(livroObjs);

        System.out.println("==================================");
        for (String[] livro : livros){
            for (int i=0;i<livro.length;i++)
                System.out.print(livro[i]+" | ");
            System.out.print(System.lineSeparator());
        }

        System.out.println("==================================");
        //2. JSON usando a biblioteca Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            objectMapper.writeValue(new File("livros.json"), livroObjs);
        }catch (Exception e){
            e.printStackTrace();
        }
        //3. XML usando a biblioteca Jackson , organizar mais a gravação
        XmlMapper xmlMapper = new XmlMapper();

        try {
            File arq = new File("livros.xml");
            for (String[] a:
                    livros) {
                xmlMapper.writeValue(arq,livroObjs);
            }
            //xmlMapper.writeValue(new File("pessoas.xml"),pessoas);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
