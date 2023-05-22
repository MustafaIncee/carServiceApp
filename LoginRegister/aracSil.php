<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['plaka'])) {
    if ($db->dbConnect()) {
        if ($db->aracSil("arababilgileri", $_POST['plaka'])) {
            echo "Arac Silme islemi Basarili!";
        } else echo "Arac Silme İslemi Basarisiz";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>