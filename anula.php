<?php 
if (isset($_REQUEST['usr']) )
{
	$usr=$_REQUEST['usr'];
	$cnx =  mysqli_connect("localhost","root","","empresa6am") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	$result = mysqli_query($cnx,"select usr from usuarios where usr = '$usr'");
	if (mysqli_num_rows($result)>0)
	{
		mysqli_query($cnx,"UPDATE usuarios SET activo='No' WHERE usr = '$usr'");	
	}
	else
	{
		echo "Usuario No existe....";
	}
	mysqli_close($cnx);
}
else
{
	echo "Debe especificar usr para anular";
}
?>