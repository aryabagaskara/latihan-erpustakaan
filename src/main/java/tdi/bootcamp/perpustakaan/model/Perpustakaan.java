package tdi.bootcamp.perpustakaan.model;


import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "perpustakaan", schema = "public")
public class Perpustakaan  {

    public int getIdPerpustakaan() {
        return idPerpustakaan;
    }

    public void setIdPerpustakaan(int idPerpustakaan) {
        this.idPerpustakaan = idPerpustakaan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_perpustakaan", updatable = false, nullable = false)
    private int idPerpustakaan;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "perpustakaan")
    private Map<String, Buku> daftarBuku;

    @Column(name = "nama_perpustakaan", length = 50)
    private String namaPerpustakaan;

    public Map<String, Buku> getDaftarBuku() {
        return daftarBuku;
    }

    public void setDaftarBuku(Map<String, Buku> daftarBuku) {
        this.daftarBuku = daftarBuku;
    }

    public String getNamaPerpustakaan() {
        return namaPerpustakaan;
    }

    public void setNamaPerpustakaan(String namaPerpustakaan) {
        this.namaPerpustakaan = namaPerpustakaan;
    }

}