/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dian Traditya
 */
import koneksi.koneksiDB;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class frmBeli extends javax.swing.JFrame {

    private DefaultTableModel model;
    /**
     * Creates new form frmBeli
     */
    String nomor, kdproduk, nmproduk, jumlah, harga, total, ubayar, ukembali;
    public frmBeli() {
        initComponents();
        
         //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tblbeli.setModel(model);
        model.addColumn("Nomor");
        model.addColumn("Kode produk");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        model.addColumn("Uang Bayar");
        model.addColumn("Uang Kembali");
    }

    public void getDataProduk(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM tbl_beli";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[8];
                obj[0]=res.getString("nomor");
                obj[1]=res.getString("kd_produk");
                obj[2]=res.getString("nm_Produk");
                obj[3]=res.getString("jumlah");
                obj[4]=res.getString("harga");
                obj[5]=res.getString("total");
                obj[6]=res.getString("ubayar");
                obj[7]=res.getString("ukembali");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void loadDataProduk(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        nomor = txtnomor.getText();
        kdproduk = txtkdproduk.getText();
        nmproduk = txtnmproduk.getText();
        jumlah = txtjumlah.getText();
        harga = txtharga.getText();
        total = txttotal.getText();
        ubayar = txtbayar.getText();
        ukembali = txtkembali.getText();
    }
    
    public void dataSelect(){
        //deklarasi variabel
        int i = tblbeli.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        txtnomor.setText(""+model.getValueAt(i,0));
        txtkdproduk.setText(""+model.getValueAt(i,1));
        txtnmproduk.setText(""+model.getValueAt(i,2));
        txtjumlah.setText(""+model.getValueAt(i,3));
        txtharga.setText(""+model.getValueAt(i,4));
        txttotal.setText(""+model.getValueAt(i,5));
        txtbayar.setText(""+model.getValueAt(i,6));
        txtkembali.setText(""+model.getValueAt(i,7));
}
    public void reset(){
        nomor = "";
        kdproduk = "";
        nmproduk = "";
        jumlah = "";
        harga = "";
        total = "";
        ubayar = "";
        ukembali = "";
        
        txtnomor.setText(nomor);
        txtkdproduk.setText(kdproduk);
        txtnmproduk.setText(nmproduk);
        txtjumlah.setText(jumlah);
        txtharga.setText(harga);
        txttotal.setText(total);
        txtbayar.setText(ubayar);
        txtkembali.setText(ukembali);
    }
    
    public void simpanDataProduk(){
         //panggil fungsi load data
        loadDataProduk();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO tbl_beli(nomor, kd_produk, nm_produk, jumlah, harga, total, ubayar, ukembali)"
                            + "VALUES('"+ nomor +"','"+ kdproduk +"','"+ nmproduk +"','"+ jumlah +"','"+ harga +"','"+ total+"','"+ ubayar +"','"+ ukembali +"')";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataProduk();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDataProduk(){
        //panggil fungsi ambil data
        loadDataProduk(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM tbl_beli WHERE nomor='"+ nomor +"'";
                PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataProduk();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtnmproduk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbeli = new javax.swing.JTable();
        txtjumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmdsimpan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdhitungtotal = new javax.swing.JButton();
        txtnomor = new javax.swing.JTextField();
        cmdhapus = new javax.swing.JButton();
        txtkdproduk = new javax.swing.JTextField();
        cmdkeluar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        txtkembali = new javax.swing.JTextField();
        cmdhitungkembali = new javax.swing.JButton();
        cmdrefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setText("Jumlah");

        tblbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbeliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblbeli);

        jLabel10.setFont(new java.awt.Font("Orator Std", 0, 18)); // NOI18N
        jLabel10.setText("PAYMENT");
        jLabel10.setToolTipText("");

        jLabel2.setText("Harga");

        jLabel3.setText("Nomor");

        cmdsimpan.setText("Simpan");
        cmdsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdsimpanMouseClicked(evt);
            }
        });
        cmdsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsimpanActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode Produk");

        jLabel7.setText("Total");

        jLabel5.setText("Nama Produk");

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 0, 36)); // NOI18N
        jLabel1.setText("O-Blonx.clt");

        cmdhitungtotal.setText("Hitung Total");
        cmdhitungtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdhitungtotalActionPerformed(evt);
            }
        });

        cmdhapus.setText("Hapus");
        cmdhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdhapusActionPerformed(evt);
            }
        });

        txtkdproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdprodukActionPerformed(evt);
            }
        });

        cmdkeluar.setText("Keluar");
        cmdkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdkeluarMouseClicked(evt);
            }
        });

        jLabel8.setText("Uang Bayar");

        jLabel11.setText("Uang Kembali");

        cmdhitungkembali.setText("Hitung Kembali");
        cmdhitungkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdhitungkembaliActionPerformed(evt);
            }
        });

        cmdrefresh.setText("Refresh");
        cmdrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7)
                                    .addComponent(cmdsimpan))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdhapus)
                                        .addGap(43, 43, 43)
                                        .addComponent(cmdkeluar)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtbayar)
                                    .addComponent(txtkembali)
                                    .addComponent(txttotal, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtharga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                    .addComponent(txtnmproduk, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtkdproduk, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtnomor, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtjumlah, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmdhitungtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdhitungkembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkdproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnmproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmdhitungtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdrefresh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cmdhitungkembali))
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdkeluar)
                    .addComponent(cmdhapus)
                    .addComponent(cmdsimpan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbeliMouseClicked

    }//GEN-LAST:event_tblbeliMouseClicked

    private void cmdsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsimpanActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_cmdsimpanActionPerformed

    private void cmdhitungtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdhitungtotalActionPerformed
    int a,b,tot;
    a=Integer.parseInt(txtjumlah.getText());
    b=Integer.parseInt(txtharga.getText());
    
    tot=a*b;
    txttotal.setText (String.valueOf(tot));
    }//GEN-LAST:event_cmdhitungtotalActionPerformed

    private void cmdhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdhapusActionPerformed
        hapusDataProduk();        // TODO add your handling code here:
    }//GEN-LAST:event_cmdhapusActionPerformed

    private void txtkdprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkdprodukActionPerformed

    private void cmdkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdkeluarMouseClicked
        this.dispose();
        new frmUtama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_cmdkeluarMouseClicked

    private void cmdhitungkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdhitungkembaliActionPerformed
    int c,d,tot;
    c=Integer.parseInt(txttotal.getText());
    d=Integer.parseInt(txtbayar.getText());
    
    tot=d-c;
    txtkembali.setText (String.valueOf(tot));        // TODO add your handling code here:
    }//GEN-LAST:event_cmdhitungkembaliActionPerformed

    private void cmdsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdsimpanMouseClicked
      simpanDataProduk();  // TODO add your handling code here:
    }//GEN-LAST:event_cmdsimpanMouseClicked

    private void cmdrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefreshActionPerformed
    int a,b;
    a = 0;

    txtnomor.setText(String.valueOf(a));
    txtkdproduk.setText(String.valueOf(a));
    txtnmproduk.setText(String.valueOf(a));
    txtjumlah.setText(String.valueOf(a));
    txtharga.setText(String.valueOf(a));
    txttotal.setText(String.valueOf(a));
    txtbayar.setText(String.valueOf(a));
    txtkembali.setText(String.valueOf(a));
    }//GEN-LAST:event_cmdrefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdhapus;
    private javax.swing.JButton cmdhitungkembali;
    private javax.swing.JButton cmdhitungtotal;
    private javax.swing.JButton cmdkeluar;
    private javax.swing.JButton cmdrefresh;
    private javax.swing.JButton cmdsimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblbeli;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkdproduk;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txtnmproduk;
    private javax.swing.JTextField txtnomor;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
