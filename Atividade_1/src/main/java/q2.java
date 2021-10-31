import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q2 {


    public static void main(String[] args) {

        List<Livro> livros = new ArrayList();
        Scanner s = new Scanner(System.in);
        int c = 0;


        while(c!=-1) {


            try {
                if (Files.notExists(Path.of("livros.csv"))) {
                    OutputStream os = new FileOutputStream("livros.csv",true);
                    OutputStreamWriter pw = new OutputStreamWriter(os);
                    StringBuilder builder = new StringBuilder();
                    Livro a =new Livro("978-0575086258", "Metro 2033", "Orion Publishing Co", "Dmitry Glukhovsky", "2011");
                    builder.append("isbn,titulo,editora,autor,ano_publicacao"+"\n");
                    builder.append(a.toString()+"\n");

                    try {
                        pw.write(builder.toString());
                        pw.flush();
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            System.out.println("========================================");
            System.out.println("==== Digite o isbn : ===================");
            System.out.println("========================================");
            String isbn = s.next();
            System.out.println("========================================");
            System.out.println("==== Digite o titulo : =================");
            System.out.println("========================================");
            String titulo = s.next();
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("==== Digite a editora : ================");
            System.out.println("========================================");
            String editora = s.next();
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("==== Digite o nome do autor : ==========");
            System.out.println("========================================");
            String autor = s.next();
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("==== Digite o ano de publicacao : ======");
            System.out.println("========================================");
            String ano_publicacao = s.next();
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("==== Parar de adicionar ? ==============");
            System.out.println("==== (1) Sim ou (0) não   ==============");
            int aux = s.nextInt();
            OutputStream os = null;
            try {
                os = new FileOutputStream("livros.csv",true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter pw = new OutputStreamWriter(os);
            StringBuilder builder = new StringBuilder();
            Livro novo = new Livro(isbn, titulo, editora,autor, ano_publicacao);
            livros.add(novo);
            builder.append(novo.toString()+"\n");

            try {
                pw.append(builder.toString());
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (aux==1) {
                c = -1;
            }
        }
    }
}
