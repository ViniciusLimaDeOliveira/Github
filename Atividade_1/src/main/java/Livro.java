import java.io.Serializable;

public class Livro implements Serializable {
    private String autor;
    private String isbn;
    private String titulo;
    private String editora;
    private String ano_publicacao;

    public Livro(String isbn, String titulo, String editora,String autor, String ano_publicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
    }



    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return this.editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno_publicacao() {
        return this.ano_publicacao;
    }

    public void setAno_publicacao(String ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String toString() {
        return this.isbn + "," + this.titulo + "," + this.editora + ","+this.autor+","+this.ano_publicacao;
    }
}
