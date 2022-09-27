<?php
if (isset($_REQUEST['correo']) && isset($_REQUEST['clave'])){
    $correo=$_REQUEST['correo'];
	$clave=$_REQUEST['clave'];
	$cnx =  mysqli_connect("localhost","root","","Empresa6am");
    $res=$cnx->query("select * from usuarios where correo = '$correo' and clave = '$clave'");
    $json = array();
	foreach ($res as $row) 
	{
		$json['datos'][]=$row;
	}
    //pasar los datos del array a JSON con información o vacío
	echo json_encode($json);
}
else
    echo "El correo y la clave son obligatorios";
?>