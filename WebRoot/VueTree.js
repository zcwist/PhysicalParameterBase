$(document).ready(function(){
  var roleMap = {
    "Administrator":"管理员",
    "Engineer":"工程师"
  };
  
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
  var userName = Request("user");
  var role = Request("Role");
  $('#user').html(userName);
  $('#role').html(roleMap[role]);



    var data = {
      	name: '被测对象',
      	objectId: null,
      	oid: null,
      	children: []
    }



    Vue.component('item', {
    	template: '#item-template',
      	data: function () {

	        return {
	          	open: false
	        }
      	},
      	computed: {
	        isFolder: function () {
	          	return typeof(this.model.oid)!='string'
        	},
        	childrenIsLoaded: function() {
        		return this.model.children &&
            		this.model.children.length
        	}

      	},
      	methods: {
	        toggle: function () {
	        	if (this.isFolder){
	        		if (!this.childrenIsLoaded){
	        			//the children of the node hasn't been loaded
	        			this.changeType();
	        			var nodeName = (this.model.name == '被测对象')? "":this.model.name;
	        			var objectId = this.model.objectId;
	        			this.loadTreeData(nodeName,objectId,this);
	        		}
	        		this.open = !this.open
	        	}
	        	else {
	        		// the node is a leaf
	        		generateTable(this.model);
	        	}
        	},
        	changeType: function () {
          		
        		this.model.$add('children', [])
        		this.open = true
          		
        	},
        	addChild: function (objectId,nodeName,oid) {
          		this.model.children.push({
            		name: nodeName,
            		objectId: objectId,
            		oid:oid
          		})
       		},
       		loadTreeData: function(nodeName,objectId,model){
       			console.log(objectId);
       			$.post("servlet/QueryServlet",{"askFor":"objectTree","objectId":objectId},function(result){
        			$.each(result.child, function(index,val){
        				model.addChild(val.objectId,val.nodeName,val.oid);
        				
        			})
        			this.open = true;
	        	})
       		}
      	}
    })

	var tableDataSample = '[{"oid":"1","value":{"atType":"杨氏模量","unit":"GPa","value":0.655,"type":"float"},"pid":"1"}]';

	var generateTable = function(nodeModel){
		var nodeName = nodeModel.name;
		$('#table-title').html(nodeName+"物性参数表");
		var objectId = nodeModel.oid;
		console.log("oid:" + objectId + "   nodeName:" + nodeName);
		$.post("servlet/QueryServlet",{"askFor":"recordTable","objectId":objectId},function(result){
			var tableHtml = '';
			$.each(result, function(index,val){
				console.log(val);
				var parameterId = val.pid;

				// $.each(val.value,function(index, val){
					tableHtml += generateTr(val.value,objectId,parameterId);

				// })

			});
			$('#recordContent').html(tableHtml);
		})

	}

	var generateTr = function(itemValue,oid,pid){
		var tr = '<tr>' + 
			'<td>' + itemValue.atType + '</td>' +
			'<td>' + itemValue.value + '</td>' +
			'<td>' + itemValue.unit + '</td>' +
			'<td><a href="RecordSample.html?oid=' + oid + '&pid=' + pid +'">more</a></td>' +
			'</tr>';
		return tr;
	}

    // boot up the demo
    var demo = new Vue({
      	el: '#demo',
      	data: {
        	treeData: data
      	}
    });



});