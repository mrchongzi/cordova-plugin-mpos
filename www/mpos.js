var exec = require('cordova/exec');
var mposinfo = {
    show:function() {
        exec(null, null, "mposinfo", "show", []);
    },
    hide:function() {
        exec(null, null, "mposinfo", "hide", []);
    }
};
module.exports = mposinfo;
 
//module.exports = { show: function(content, type){ exec(null,null,"Toast","show",[content,type]); } }