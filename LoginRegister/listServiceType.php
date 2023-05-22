<?php
require_once "DataBaseConfig.php";

    $db = new DataBaseConfig(); 
    $conn = $db->conn; 

    $sql = "SELECT * FROM arababilgileri WHERE kullanici_id ";

    $result = $conn->query($sql);

    if (!$result) {
        echo "Error in executing query: " . mysqli_error($conn);
    } else {
        $return_arr = array();
        $return_arr['arababilgileri'] = array();
        while ($row = $result->fetch_array()) {
            array_push($return_arr['arababilgileri'], array(
                'plaka' => $row['plaka']
            ));
        }
        echo json_encode($return_arr);
    }
?>