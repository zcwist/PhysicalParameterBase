$(document).ready(function() {
	function Request(strName) 
	{ 
		var strHref = window.document.location.href; 
		var intPos = strHref.indexOf("?"); 
		var strRight = strHref.substr(intPos + 1); 

		var arrTmp = strRight.split("&"); 
		for(var i = 0; i < arrTmp.length; i++) 
		{ 
		var arrTemp = arrTmp[i].split("="); 

		if(arrTemp[0].toUpperCase() == strName.toUpperCase()) return arrTemp[1]; 
		} 
		return ""; 
	}
	var oid = Request('oid');
	var pid = Request('pid');
	$.post('servlet/QueryServlet', {"askFor":"sampleTable","objectId":oid,"parameterId":pid}, function(result) {
		/*optional stuff to do after success */
		console.log(result);
	});

});