<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['marka']) && isset($_POST['model']) && isset($_POST['yil']) && isset($_POST['plaka'])&& isset($_POST['kilometre'])&& isset($_POST['yakit'])) {
    if ($db->dbConnect()) {
        if ($db->carInformation("arababilgileri", $_POST['marka'], $_POST['model'], $_POST['yil'], $_POST['plaka'], $_POST['kilometre'], $_POST['yakit'])) {
            echo "Arac Kaydi Basarili!";
        } else echo "Arac Kaydi Basarisiz";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?> 
