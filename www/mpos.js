
var exec = require('cordova/exec');
module.exports = exec(
	function(a_){alert(a_);},null,"val_","val_1_");
 
//module.exports = { show: function(content, type){ exec(null,null,"Toast","show",[content,type]); } }