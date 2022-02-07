(function(factory)
{
    "use strict";
    // Soporte para los tres tipos de carga posibles
    if (typeof require === "function" && typeof exports === "object" && typeof module === "object") {
        // [1] CommonJS/Node.js. No Aplica para esta libreria.
        var target = module["exports"] || exports; // module.exports para Node.js
        factory(0, target, require);
    } else if (typeof define === "function" && define["amd"]) {
        // [2] AMD - require.js
        define(["exports", "require"], function(exports, require) {
            factory(1, exports, require);
        });
    } else {
        // [3] Carga por defecto (Tag <script> plano)
        factory(2);
    }
}
(function(tipoCarga, exports, require)
{
    var ajax2controllerLib = function(){
	
        ////////////////////////////////////////////////
        ////	Libreria de invocacion AJAX-SPRING	////
        ////			  Version: 1.0				////
		////////////////////////////////////////////////
		
		/***
		*	Metodo para lanzar una peticion AJAX contra un controlador
		* 	(spring) y unos datos de entrada.
		
		*	Requiere:
		*		- url:		la url completa del controlador a invocar
		*		- data:		los datos a enviar que se pasaran por POST
		*		- callBack: funcion de vuelta aplicativa
		*/
        var flanzarPeticion = function (params)
		{	
        	// Se recupera el id de la tarea de la ventana
        	// almacenado a través del control-tareas.tagx
			var tareaID = "";
			
			if (typeof idTarea != 'undefined')
				tareaID = idTarea;
			
			$.ajax({
				url: params.url,
				type: "POST",
				data: {
					'ArqSpring.idTarea' : tareaID,
					'data' : params.data
				},
				beforeSend: function (xhr) {
					xhr.setRequestHeader('ArqSpring.idTarea', tareaID);
				},
				success: function (responseData, textStatus) {
					return params.callBack (responseData, textStatus);
				},
				error: function (responseData, textStatus) {
					return params.callBack (responseData, textStatus);
				}
			});
        };
		
        return {
            lanzarPeticion: function (params) {
                return flanzarPeticion (params);
            }
        };
    }();
	
	
    var ajax2controller = null;
    var cargaRequireARQ = null;
	
	// Se comprueba que tipo de carga de la libreria se ha realizado
    switch (tipoCarga) {
        case 0:
        	// Carga NodeJS
            cargaCommonsJS(exports, require);
            break;
			
        case 1:
        	// Carga por require.js
            cargaRequireARQ = configurarRequire();
            cargaAMD(exports, cargaRequireARQ);
            break;
			
        case 2:
        	// Carga por defecto
            carga();
            break;
    }
	
	
    /***
    *	Metodo de configuracion de require
    */
    function configurarRequire() 
	{}
	
	
    /***
    *	Metodo de carga por medio de CommonsJS o Node.js
    */
    function cargaCommonsJS(exports, require) 
	{       
		//require("/atcl_es_web_pub/js/json2.js");
        ajax2controller = exports;
		
        ajax2controller.exportSymbol = function(path, object) {
            var tokens = path.split(".");
            exports[tokens[tokens.length - 1]] = object;
        };

        ajax2controller.exportSymbol("lanzarPeticion", function (params)  {
            return ajax2controllerLib.lanzarPeticion (params);
        });
    }
	
	
    /***
    *	Metodo de carga por medio de require.js o AMD
    */
    function cargaAMD(exports, cargaRequireARQ)
	{
        //cargaRequireARQ(["json2"], function(json2) {});
        ajax2controller = exports;
		
        ajax2controller.exportSymbol = function(path, object) {
            var tokens = path.split(".");
            exports[tokens[tokens.length - 1]] = object;
        };

        ajax2controller.exportSymbol("lanzarPeticion", function (params) {
            return ajax2controllerLib.lanzarPeticion (params);
        });
    }	

	/***
	*	Metodo de carga por medio del Tag <script> plano
	*/
    function carga()
	{
        ajax2controller = ajax2controllerLib;
        window["ajax2controller"] = ajax2controllerLib;
    }
	
    return ajax2controller;
}));