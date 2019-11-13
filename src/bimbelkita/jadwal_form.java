/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbelkita;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public final class jadwal_form extends javax.swing.JFrame {

    /**
     * Creates new form jadwal_form
     */
    private DefaultTableModel tabmode;
    private DefaultComboBoxModel combolist;
    private ResultSet hasil;

    CRUDjadwal crud = new CRUDjadwal();

    public jadwal_form() {
        initComponents();

        tampilTingkat();
        tampilKelas();
        tampilRuang();
        tampilHari();
        tampilJadwal();
        tampilNamaGuru();
        tampilBidangStudi();
        tampilJam();
        setLocationRelativeTo(this);
    }

    public void tampilJadwal() {
        Object[] baris = {"ID", "Hari", "Jam", "Guru", "Bidang Studi", "Kelas", "Ruang"};
        tabmode = new DefaultTableModel(null, baris);
        tabel_jadwal.setModel(tabmode);
        try {

            crud.setRuang(combo_ruang.getSelectedItem().toString());
            crud.setHari(combo_hari.getSelectedIndex());
            hasil = crud.tampilJadwal(crud.getRuang(), crud.getHari());
            while (hasil.next()) {
                crud.setTabId(hasil.getInt("id"));
                crud.setTabHari(hasil.getString("hari"));
                crud.setTabJam(hasil.getString("jam"));
                crud.setTabGuru(hasil.getString("namaGuru"));
                crud.setTabBidang(hasil.getString("bidangGuru"));
                crud.setTabKelas(hasil.getString("namaKelas"));
                crud.setTabRuang(hasil.getString("namaRuang"));

                String[] data = {String.valueOf(crud.getTabId()), crud.getTabHari(), crud.getTabJam(), crud.getTabGuru(), crud.getTabBidang(), crud.getTabKelas(), crud.getTabRuang()};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("jadwal" + e);
        }
    }

    public void tampilHari() {
        String[] hari = {"senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu"};
        combolist = new DefaultComboBoxModel(hari);
        combo_hari.setModel(combolist);
        crud.setHari(combo_hari.getSelectedIndex());
    }

    public void tampilBidangStudi() {
        String[] bidangStudi = {"Bhs. Indonesia", "Bhs. Inggris", "Matematika", "IPA", "IPS"};
        combolist = new DefaultComboBoxModel(bidangStudi);
        combo_bidangStudi.setModel(combolist);
        crud.setBidangStudi(combo_bidangStudi.getSelectedIndex());
    }

    public void tampilJam() {

        try {
            crud.setHari(combo_hari.getSelectedIndex());
            crud.setNamaRuang(combo_ruang.getSelectedItem().toString());
            ArrayList<String> jamarr = new ArrayList<String>();

            hasil = crud.tampilJam(crud.getHari(), crud.getNamaRuang());
            while (hasil.next()) {
                crud.setTabJam(hasil.getString("jam"));
                String data = crud.getTabJam();
                jamarr.add(data);
            }
            crud.setJumId(crud.getHari(), crud.getNamaRuang());
            if (jamarr.isEmpty() && crud.getJumId() == 2) {
                jamarr.add("jadwal telah penuh");
                combo_jamKelas.disable();
                String[] jam_kelas = new String[jamarr.size()];
                jam_kelas = jamarr.toArray(jam_kelas);
                combolist = new DefaultComboBoxModel(jam_kelas);
                combo_jamKelas.setModel(combolist);

            } else if (crud.getJumId() == 0) {
                String[] jam = {"15:00 - 17:00", "18:00 - 20:00"};
                combolist = new DefaultComboBoxModel(jam);
                combo_jamKelas.setModel(combolist);
                combo_jamKelas.enable();

            } else {
                combo_jamKelas.enable();
                String[] jam_kelas = new String[jamarr.size()];
                jam_kelas = jamarr.toArray(jam_kelas);
                combolist = new DefaultComboBoxModel(jam_kelas);
                combo_jamKelas.setModel(combolist);
            }

        } catch (SQLException ex) {
            Logger.getLogger(jadwal_form.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Date date = new Date();
//        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
//        spinner_jam.setModel(sm);
//        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner_jam, "HH:mm:ss");
//        spinner_jam.setEditor(de);
//        System.out.println(spinner_jam.getValue().toString());
    }

    public void tampilTingkat() {
        String[] tingkat = {"kelas 7", "kelas 8", "kelas 9"};
        combolist = new DefaultComboBoxModel(tingkat);
        combo_tingkat.setModel(combolist);
    }

    public void tampilNamaGuru() {
        try {
            crud.setJam(combo_jamKelas.getSelectedIndex());
            crud.setHari(combo_hari.getSelectedIndex());
            crud.setBidangStudi(combo_bidangStudi.getSelectedIndex());
            crud.setRuang(combo_ruang.getSelectedItem().toString());
            ArrayList<String> Guruarr = new ArrayList<String>();
            hasil = crud.tampilGuru(crud.getbidangStudi(), crud.getJam(), crud.getHari(), crud.getTabRuang());
            while (hasil.next()) {
                crud.setNamaGuru(hasil.getString("nama"));
                String data = crud.getNamaGuru();
                Guruarr.add(data);
            }
            if (Guruarr.isEmpty()) {
                Guruarr.add("guru tidak ada");
                combo_namaGuru.disable();

            } else {
                combo_namaGuru.enable();
            }
            String[] namaGuru = new String[Guruarr.size()];

            namaGuru = Guruarr.toArray(namaGuru);

            combolist = new DefaultComboBoxModel(namaGuru);
            combo_namaGuru.setModel(combolist);
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public void tampilKelas() {
        try {
            crud.setTingkat(combo_tingkat.getSelectedIndex());
            ArrayList<String> kelasarr = new ArrayList<String>();
            hasil = crud.tampilKelas(crud.getTingkat());
            while (hasil.next()) {
                crud.setNamaKelas(hasil.getString("nama"));
                String data = crud.getNamaKelas();
                kelasarr.add(data);
            }
            String[] kelas = new String[kelasarr.size()];
            kelas = kelasarr.toArray(kelas);
            combolist = new DefaultComboBoxModel(kelas);
            combo_kelas.setModel(combolist);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void tampilRuang() {
        try {
            ArrayList<String> ruangarr = new ArrayList<String>();
            hasil = crud.tampilRuang();
            while (hasil.next()) {
                crud.setNamaRuang(hasil.getString("nama"));
                String data = crud.getNamaRuang();
                ruangarr.add(data);
            }
            String[] ruang = new String[ruangarr.size()];
            ruang = ruangarr.toArray(ruang);
            combolist = new DefaultComboBoxModel(ruang);
            combo_ruang.setModel(combolist);
        } catch (Exception e) {
        }

    }

    public void reset_text() {
        txt_id.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        combo_kelas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_jadwal = new javax.swing.JTable();
        combo_ruang = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        combo_tingkat = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        combo_jamKelas = new javax.swing.JComboBox<>();
        combo_bidangStudi = new javax.swing.JComboBox<>();
        combo_namaGuru = new javax.swing.JComboBox<>();
        combo_hari = new javax.swing.JComboBox<>();
        txt_id = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_siswa = new javax.swing.JMenuItem();
        menu_guru = new javax.swing.JMenuItem();
        menu_kelas = new javax.swing.JMenuItem();
        menu_jadwal = new javax.swing.JMenuItem();
        menu_ruang = new javax.swing.JMenuItem();
        menu_keluar = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));

        combo_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bidang Studi");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Guru");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelas");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jam Kelas");

        tabel_jadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_jadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_jadwalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_jadwal);

        combo_ruang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_ruang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_ruangItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ruang");

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Form Jadwal");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Tingkat");

        combo_tingkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_tingkat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_tingkatItemStateChanged(evt);
            }
        });
        combo_tingkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tingkatActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Hari");

        combo_jamKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_jamKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_jamKelasItemStateChanged(evt);
            }
        });

        combo_bidangStudi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_bidangStudi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_bidangStudiItemStateChanged(evt);
            }
        });

        combo_namaGuru.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_hari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_hari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_hariItemStateChanged(evt);
            }
        });

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(32, 32, 32)
                                .addComponent(combo_hari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(0, 29, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(combo_tingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel3)))
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_jamKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 15, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_ruang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(22, 22, 22))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(combo_bidangStudi, 0, 152, Short.MAX_VALUE)
                                .addComponent(combo_namaGuru, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reset)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(combo_bidangStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(combo_namaGuru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(combo_jamKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(combo_hari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_tingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_reset)
                            .addComponent(btn_delete)
                            .addComponent(btn_insert)
                            .addComponent(btn_update))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");

        menu_siswa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_siswa.setText("Form Siswa");
        menu_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_siswaActionPerformed(evt);
            }
        });
        jMenu1.add(menu_siswa);

        menu_guru.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menu_guru.setText("Form Guru");
        menu_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_guruActionPerformed(evt);
            }
        });
        jMenu1.add(menu_guru);

        menu_kelas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        menu_kelas.setText("Form Kelas");
        menu_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_kelasActionPerformed(evt);
            }
        });
        jMenu1.add(menu_kelas);

        menu_jadwal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        menu_jadwal.setText("Form Jadwal");
        menu_jadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_jadwalActionPerformed(evt);
            }
        });
        jMenu1.add(menu_jadwal);

        menu_ruang.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menu_ruang.setText("Form Ruang");
        menu_ruang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ruangActionPerformed(evt);
            }
        });
        jMenu1.add(menu_ruang);

        jMenuBar1.add(jMenu1);

        menu_keluar.setText("Keluar");
        menu_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_keluarMouseClicked(evt);
            }
        });
        jMenuBar1.add(menu_keluar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_tingkatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_tingkatItemStateChanged
        tampilKelas();
    }//GEN-LAST:event_combo_tingkatItemStateChanged

    private void combo_tingkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tingkatActionPerformed
        tampilKelas();
    }//GEN-LAST:event_combo_tingkatActionPerformed

    private void combo_bidangStudiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_bidangStudiItemStateChanged
        tampilNamaGuru();
    }//GEN-LAST:event_combo_bidangStudiItemStateChanged

    private void combo_ruangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_ruangItemStateChanged
        if (txt_id.getText().trim().equals("")) {
            tampilJadwal();

        }
        tampilNamaGuru();
        tampilJam();

    }//GEN-LAST:event_combo_ruangItemStateChanged

    private void combo_hariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_hariItemStateChanged
        if (txt_id.getText().trim().equals("")) {
            tampilJadwal();

        }
        tampilNamaGuru();
        tampilJam();
    }//GEN-LAST:event_combo_hariItemStateChanged

    private void combo_jamKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_jamKelasItemStateChanged
        tampilNamaGuru();
    }//GEN-LAST:event_combo_jamKelasItemStateChanged

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        if (combo_namaGuru.getSelectedItem().toString().equals("guru tidak ada")) {
            JOptionPane.showMessageDialog(null, "Maaf, tidak ada guru yang ada");

        } else if (combo_jamKelas.getSelectedItem().equals("jadwal telah penuh")) {
            JOptionPane.showMessageDialog(null, "Maaf, jadwal pada ruang ini telah penuh, mohon untuk memilih ruangan lainya");
        } else {
            try {
                crud.setIdGuru(combo_namaGuru.getSelectedItem().toString());
                crud.setIdKelas(combo_kelas.getSelectedItem().toString());
                crud.setNamaRuang(combo_ruang.getSelectedItem().toString());
                crud.setTabHari(combo_hari.getSelectedItem().toString());
                crud.setTabJam(combo_jamKelas.getSelectedItem().toString());
                crud.simpanData(crud.getIdGuru(), crud.getIdKelas(), crud.getNamaRuang(), crud.getTabHari(), crud.getTabJam());
                tampilJadwal();
                tampilJam();
                tampilNamaGuru();
            } catch (Exception e) {
                System.out.println("insert jadwal:" + e.getMessage());
            }
        }

    }//GEN-LAST:event_btn_insertActionPerformed

    private void tabel_jadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_jadwalMouseClicked
        try {
            int row = tabel_jadwal.rowAtPoint(evt.getPoint());
            String id = tabel_jadwal.getValueAt(row, 0).toString();
            String hari = tabel_jadwal.getValueAt(row, 1).toString();
            String jam = tabel_jadwal.getValueAt(row, 2).toString();
            String namaGuru = tabel_jadwal.getValueAt(row, 3).toString();
            String bidang = tabel_jadwal.getValueAt(row, 4).toString();
            String kelas = tabel_jadwal.getValueAt(row, 5).toString();
            crud.setTabJam(jam);
            String ruang = tabel_jadwal.getValueAt(row, 6).toString();
            txt_id.setText(id);
            combo_ruang.setSelectedItem(ruang);
            combo_hari.setSelectedItem(hari);
            combo_jamKelas.setSelectedItem(jam);
            combo_kelas.setSelectedItem(kelas);
            combo_bidangStudi.setSelectedItem(bidang);
            combo_namaGuru.setSelectedItem(namaGuru);
        } catch (Exception e) {
            System.out.println("tabel jadwal :" + e.getMessage());
        }
    }//GEN-LAST:event_tabel_jadwalMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (combo_namaGuru.getSelectedItem().toString().equals("guru tidak ada")) {
            JOptionPane.showMessageDialog(null, "Maaf, tidak ada guru yang ada");
        } else if (txt_id.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, ID belum diisi atau klik pada tabel untuk memilih ID");
        } else {
            try {
                crud.setTabId(Integer.parseInt(txt_id.getText()));
                crud.setIdGuru(combo_namaGuru.getSelectedItem().toString());
                crud.setIdKelas(combo_kelas.getSelectedItem().toString());
                crud.setNamaRuang(combo_ruang.getSelectedItem().toString());
                crud.setTabHari(combo_hari.getSelectedItem().toString());
                if (combo_jamKelas.getSelectedItem().equals("jadwal telah penuh")) {

                } else {
                    crud.setTabJam(combo_jamKelas.getSelectedItem().toString());
                }

                crud.ubahData(crud.getTabId(), crud.getIdGuru(), crud.getIdKelas(), crud.getNamaRuang(),
                        crud.getTabHari(), crud.getTabJam());
                reset_text();
                tampilJadwal();
                tampilJam();
                tampilNamaGuru();
            } catch (Exception e) {
                System.out.println("insert jadwal:" + e.getMessage());
            }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (txt_id.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, ID belum diisi !");
            txt_id.requestFocus();
        } else {
            if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ini ? ", "  Warning", 2) == JOptionPane.YES_OPTION) {
                String id = "";
                try {
                    crud.setTabId(Integer.parseInt(txt_id.getText()));
                    crud.hapusData(crud.getTabId());
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Informasi",
                            JOptionPane.INFORMATION_MESSAGE);
                    tampilJadwal();
                    tampilNamaGuru();
                    tampilJam();
                    reset_text();
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus", "Informasi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        reset_text();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void menu_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_siswaActionPerformed
        this.dispose();
        Siswa_Form sf = new Siswa_Form();
        sf.show();
    }//GEN-LAST:event_menu_siswaActionPerformed

    private void menu_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_guruActionPerformed
        this.dispose();
        Guru_Form gf = new Guru_Form();
        gf.show();
    }//GEN-LAST:event_menu_guruActionPerformed

    private void menu_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_kelasActionPerformed
        this.dispose();
        Kelas_Form kf = new Kelas_Form();
        kf.show();
    }//GEN-LAST:event_menu_kelasActionPerformed

    private void menu_jadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_jadwalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_jadwalActionPerformed

    private void menu_ruangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ruangActionPerformed
        this.dispose();
        Ruang_Form rf = new Ruang_Form();
        rf.show();
    }//GEN-LAST:event_menu_ruangActionPerformed

    private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan keluar ?", "Warning", 2)
                == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menu_keluarMouseClicked

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
            java.util.logging.Logger.getLogger(jadwal_form.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jadwal_form.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jadwal_form.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jadwal_form.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new jadwal_form().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> combo_bidangStudi;
    private javax.swing.JComboBox<String> combo_hari;
    private javax.swing.JComboBox<String> combo_jamKelas;
    private javax.swing.JComboBox<String> combo_kelas;
    private javax.swing.JComboBox<String> combo_namaGuru;
    private javax.swing.JComboBox<String> combo_ruang;
    private javax.swing.JComboBox<String> combo_tingkat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menu_guru;
    private javax.swing.JMenuItem menu_jadwal;
    private javax.swing.JMenuItem menu_kelas;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JMenuItem menu_ruang;
    private javax.swing.JMenuItem menu_siswa;
    private javax.swing.JTable tabel_jadwal;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
