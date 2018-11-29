package tdi.bootcamp.perpustakaan.model;

import javax.persistence.*;

@Entity
@Table(name = "buku", schema = "public")
public class Buku {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_buku", updatable = false, nullable = false)
    private int idBuku;

    @Column(name = "judul_buku")
    private String title;

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    @Column(name = "author_buku", length = 50)
    private String author;

    @Column(name="status_pinjam_buku")
    private boolean isPinjam;

    @OneToOne
    @JoinColumn(name = "id_peminjam")
    private  Peminjam  yangPeminjam;

    @OneToOne
    @JoinColumn(name = "id_perpustakaan")
    private Perpustakaan perpustakaan;


    public Perpustakaan getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(Perpustakaan perpustakaan) {
        this.perpustakaan = perpustakaan;
    }

    public Peminjam getYangPeminjam() {
        return yangPeminjam;
    }

    public void setYangPeminjam(Peminjam yangPeminjam) {
        this.yangPeminjam = yangPeminjam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isPinjam() {
        return isPinjam;
    }

    public void setPinjam(boolean isPinjam) {
        this.isPinjam = isPinjam;
    }

}