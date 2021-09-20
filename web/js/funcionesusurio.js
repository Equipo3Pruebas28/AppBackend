/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("tr #deleteUser").click(function(e){
        e.preventDefault();
        var id= $(this).parent().find('#codigo').val();
        Swal.fire({
            title: 'Está seguro?',
            text: "No podrás revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminar!'
        }).then((result) => {
            if (result.isConfirmed) {
                Eliminar(id);
                Swal.fire(
                        'Eiminado!',
                        'Su archivo ha sido eliminado.',
                        'success'
                        )
            }
        });

    });
    
    function Eliminar(id){
        var url= "Controlador?menu=Usuarios&accion=Eliminar&id="+ id;
        console.log("eliminado");
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            success: function (r){
                
            }
        });
    }
        
    
});

