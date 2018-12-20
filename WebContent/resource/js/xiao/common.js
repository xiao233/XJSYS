/**
 * 通过form表单得到json格式数据
 */
function getJsonByFormId(formId){
	var inputItems = $("#"+formId+">input");
	var json={};
	for (var i = 0; i < inputItems.length; i++) {
		if(inputItems[i]!==undefined && inputItems[i].name!==""){
			json[inputItems[i].name]=inputItems[i].value;
		}
	}
	return json;
}