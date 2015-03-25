##API

Post http://ip:8080/PhysicalParameterBase/servlet/QueryServlet

Parameter:
{
	'askFor':'objectTree',
	'parentNode':nodeName
}

Returns:
nodeName的子节点
e.g. {"child":[{"_id":"介质类型:美元"}]}


Post http://ip:8080/PhysicalParameterBase/servlet/QueryServlet

Parameter:
{
	'askFor':'recordTable',
	'objectId':objectId
}

Returns:
object的所有recordType
e.g. '[{"oid":"1","value":[{"atType":"杨氏模量","unit":"GPa","value":0.65,"type":"float"}],"pid":"1"},{"oid":"1","value":[{"atType":"长","unit":"mm","value":155.9057142857143,"type":"float"},{"atType":"宽","unit":"mm","value":65.71428571428571,"type":"float"}],"pid":"3"}]';

Post http://ip:8080/PhysicalParameterBase/servlet/QueryServlet

Parameter:
{
	'askFor':'sampleTable',
	'objectId':objectId,
	'parameterId':parametgerId
}

Returns:
Object下的具有ParameterID的Sample记录。



