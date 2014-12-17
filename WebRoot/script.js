$(document).ready(function() {
	var alldataarr = [];
	var allPhoneArr = [];
	var num;

	$.get('servlet/Query?What=SignInList', function(data) {
		/*optional stuff to do after success */
		console.log(data);
		num = data.length-1;
		$.each(data, function(index, val) {
			 /* iterate through array or object */
			 alldataarr[index] = val.name;
			 allPhoneArr[index] = val.phone;
		});
		$("#start").removeAttr('disabled');
		$("#end").removeAttr('disabled');
		console.log(alldataarr);
	});
	$("#start").click(function(event) {
		/* Act on the event */
		console.log("click start");
		start();
	});
	$("#end").click(function(event) {
		/* Act on the event */
		console.log("click end");
		ok();
	});  
	var timer;
	function change(){   
		$("#result").html(alldataarr[GetRnd(0,num)]);   
	}
	function start(){  
		console.log("start");
		clearInterval(timer);   
		timer = setInterval(change,46); //随机数据变换速度，越小变换的越快   
	}
	function ok(){   
		clearInterval(timer); 
		index = GetRnd(0,num);
		str = alldataarr[index] + "<br>" + allPhoneArr[index];
		$("#result").html(str);   
	}   
	function GetRnd(min,max){   
		return Math.round((Math.random()*(max-min+1)));   
	}

});