package tdi.bootcamp.perpustakaan;

import org.hibernate.Session;
import tdi.bootcamp.perpustakaan.util.HibernateUtil;
import tdi.bootcamp.perpustakaan.model.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

    private static String getNativeQuery(Session session, String sql) {
        return (String) session.createNativeQuery(sql).getSingleResult();
    }

    //perpus+buku

    private static Integer simpanPerpustakaan(Session session){

        Perpustakaan perpustakaan = new Perpustakaan();
        Map<String,Buku> mapBuku = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            Buku buku = new Buku();
            buku.setTitle("buku seru edisi "+i);
            buku.setAuthor("bambang");
            buku.setPerpustakaan(perpustakaan);
            buku.setPinjam(false);
            mapBuku.put("B0"+i,buku);
        }
        perpustakaan.setNamaPerpustakaan("Perpustakaan Asgar");
        perpustakaan.setDaftarBuku(mapBuku);
        return (Integer) session.save(perpustakaan);
    }

    //insert buku ke perpus
    private static Integer simpanBuku(Session session){
        Perpustakaan perpustakaan = session.find(Perpustakaan.class,1);
        Map<String,Buku> mapBuku = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            Buku buku = new Buku();
            buku.setTitle("Naruto Piece Volume "+i);
            buku.setAuthor("Takeshi Castle");
            buku.setPerpustakaan(perpustakaan);
            buku.setPinjam(false);
            mapBuku.put("C0"+i,buku);
        }
        perpustakaan.setDaftarBuku(mapBuku);
        return (Integer) session.save(perpustakaan);
    }

/*
    private static Buku getBuku(Session session) {
        Buku listBuku =(Buku) session.createQuery("select b.yangPeminjam from Buku b where b.idBuku = :id_buku")
                .setParameter("id_buku",5)
                .getResultList().get(0);
        return listBuku;
    }*/




    private static Integer simpanPeminjam(Session session){
        Perpustakaan perpustakaan = session.find(Perpustakaan.class,1);
        Peminjam peminjam = new Peminjam();
        peminjam.setAlamat("Jalan Panas Terik");
        peminjam.setNama("CTR");
        peminjam.setPerpustakaan(perpustakaan);
        return (Integer) session.save(peminjam);
    }

    private static void pinjamBuku(Session session){
        Peminjam peminjam = session.find(Peminjam.class,10);
        Perpustakaan perpustakaan = new Perpustakaan();
        Map<String,Buku> mapBuku = new HashMap<>();
        Buku buku = session.find(Buku.class,5);
        buku.setPinjam(true);
        buku.setYangPeminjam(peminjam);
        mapBuku.put("Pinjam ",buku);
        peminjam.setDaftarBuku(mapBuku);
        session.update(buku);
        session.update(peminjam);

    }

    private static void statusBuku(Session session){
        Buku buku = session.find(Buku.class,5);
        if (buku.isPinjam()==true){
            System.out.println("Buku sedang dipinjam");
        }
        else{
            System.out.println("Buku tidak sedang dipinjam");
        }
    }


    private static int updateBukuHql(Session session) {
        return session.createQuery("update Buku set title = :nama, author = :tgl where idBuku = :id")
                .setParameter("nama", "Joko Subianto").setParameter("tgl", "Hendra")
                .setParameter("id", 8).executeUpdate();
    }

    //bisa mencari nama buku yg dipinjam berdasar nama peminjam (blm)
    /*
    private static Map<String, Buku> getListBuku(Session session) {
        Map<String, Buku> listBuku =
                (Map<String, Buku>) session.createQuery("select p from Peminjam p where p.nama = :namaKota ")
                        .setParameter("namaKota", "Anne Frank")
                        .getResultList();
        return new HashMap<String,Buku>(listBuku);
    }


    private static Map<String,Buku> getListBuku(Session session) {
        Perpustakaan perpustakaan = session.find(Perpustakaan.class,1);
        Map<String,Buku> bukuMap = perpustakaan.getDaftarBuku();
        return new HashMap<>(bukuMap);
    }*/




    public static void main( String[] args)
    {
        System.out.println( "Hello World!" );


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        //App.simpanPerpustakaan(session);
        //App.simpanBuku(session);
        //App.simpanPeminjam(session);

        session.flush();
        //App.updateBukuHql(session);
        App.getBuku(session);
        /*
        Map<String,Buku> bukuMap= App.getListBuku(session);
        for (Iterator iterator = bukuMap.entrySet().iterator(); iterator.hasNext();) {
            Buku buku= (Buku) iterator.next();
            System.out.println(String.format("nama == %s", buku.getTitle()));
        }*/
        /*Buku bukuBar = getBuku(session);
        System.out.println(bukuBar.getYangPeminjam().getNama().toString());

*/




        session.close();
        HibernateUtil.shutdown();



    }
}
