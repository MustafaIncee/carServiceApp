<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['hizmetTuru'])) {
    if ($db->dbConnect()) {
        if ($db->Services("servisBilgileri", $_POST['hizmetTuru'])) {
            echo "Servis Kaydi Basarili!";
        } else echo "Servis Kaydi Basarisiz";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?> 