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
		
		var title = result.ObjectName + result.content[0].data.atType;
		// console.log("title:" + title);
		genTableTitle(title);
		genTableContent(result.content)

	});

	function genTableTitle(title){
		

		$('#table-title').html(title);
	}

	function genTableHeader(header){
		var html;
		for (var key in header.title){
			html += '<th>' + key + '</th>';
		}
		html += '<th>' + header.data.atType + '</th>';
		for (var key in header.otherInfo){
			html += '<th>' + key + '</th>';
		}
		$('#header-row').html(html);

	}

	function genTableContent(content){

		genTableHeader(content[0]);
		var html;
		$.each(content, function(index, val) {
			console.log(val);
			html += '<tr>';
			for (var key in val.title){
				html += '<th>' + val.title[key] + '</th>';
			}
			if (val.data['type'] == 'float'){
				html += '<th>' + val.data.value + ' ' +val.data.unit + '</th>';
			}
			for (var key in val.otherInfo){
				console.log(key);
				console.log(val.otherInfo.key);
				html += '<th>' + val.otherInfo[key]+ '</th>';
			}
			html += '</tr>';

		});
		$('#content').html(html);
	}

});