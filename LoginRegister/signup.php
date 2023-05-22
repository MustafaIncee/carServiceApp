<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['kullaniciAd']) && isset($_POST['email']) && isset($_POST['kullaniciSoyad']) && isset($_POST['sifre'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("kullanicibilgileri", $_POST['kullaniciAd'], $_POST['email'], $_POST['kullaniciSoyad'], $_POST['sifre'])) {
            echo "Kayit Olma islemi Basarili!";
        } else echo "Kayit Olma İslemi Basarisiz";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
