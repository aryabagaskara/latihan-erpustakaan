package tdi.bootcamp.perpustakaan.model;
import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "peminjam", schema = "public")
public class Peminjam  {

    public int getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(int idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_peminjam", updatable = false, nullable = false)
    private int idPeminjam;

    @Column(name = "nama_peminjam", length = 50)
    private String nama;

    @Column(name = "alamat_peminjam")
    private String alamat;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY,mappedBy = "perpustakaan")
    private Map<String, Buku> daftarBuku;

    @OneToOne
    @JoinColumn(name = "id_perpustakaan")
    private Perpustakaan perpustakaan;

    public Perpustakaan getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(Perpustakaan perpustakaan) {
        this.perpustakaan = perpustakaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Map<String, Buku> getDaftarBuku() {
        return daftarBuku;
    }

    public void setDaftarBuku(Map<String, Buku> daftarBuku) {
        this.daftarBuku = daftarBuku;
    }

}