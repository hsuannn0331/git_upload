
const HashMap = function() {
    
    let obj = {}

    return {
        put: function(k, v) {
            obj[k] = v;
        },
        keys: function() {
            return Object.keys(obj);
        },
        contains: function(k) {
            return (k in obj);
        },
        get: function(k) {
            return obj[k];
        },
        clear: function() {
           for(let key in obj){
            delete obj[key];
           }
        }
    };
};