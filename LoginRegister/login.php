<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['sifre'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("kullanicibilgileri", $_POST['email'], $_POST['sifre'])) {
            echo "Giris Basarili!";
        } else echo "Kullanici adi veya sifre hatali";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
