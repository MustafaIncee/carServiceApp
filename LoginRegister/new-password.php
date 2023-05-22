<?php

if(!empty($_POST['email']) && !empty($_POST['otp']) && !empty($_POST['new-password'])){
    $email = $_POST['email'];
    $otp = $_POST['otp'];
    $new_password = password_hash($_POST['new-password'], PASSWORD_DEFAULT);
    $con = mysqli_connect("localhost", "root", "", "arabaservisi");
    if($con){
        $sql = "UPDATE kullanicibilgileri SET sifre = '".$new_password."', reset_password_otp = '' ,
         reset_password_created_at = ''WHERE email = '"
            .$email."' and reset_password_otp = '" .$otp. "'";
        if (mysqli_query($con, $sql)) {
            if (mysqli_affected_rows($con)){
                echo "success";
            } else echo "Reset password failed";
        } else echo "Reset password failed";
    }else echo"Database connection failed";
}else echo " All fields are required";

