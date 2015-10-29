
var exec = require('cordova/exec');
//module.exports = exec();
 
module.exports = { show: function(content, type){ 
	exec(null,null,"Toast","show",[content,type]); } }