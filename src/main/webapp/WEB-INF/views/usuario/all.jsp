#set($titulo = "Usuarios - Todos")
#set($viewName = "usuarios") 
##set($scripts = "login/_includes.jsp")
#set($css =  ["usuario/um.css", "usuario.dois.css"] )

<div class="usuario">
	<h1>$msg.usuario.cadastrados</h1>
	<table>
		<thead>
			<tr>
				<td>$msg.id</td>
				<td>$msg.nome</td>
			</tr>
		</thead>
		<tbody>
			#foreach ($usuario in $usuarios)
				<tr>
					<td>$usuario.id</td>
					<td>$usuario.nome</td>
				</tr>
			#end
		</tbody>
	</table>
</div>

#define( $script )
	
	#if( $listTool.size($usuarios) > 3 )
	<script>
		$(document).ready(function() {
			alert("Temos mais de 3 usuarios, voce deve ter inserido algum!!");
		});
	</script>
	#end

#end