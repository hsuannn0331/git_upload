
const HashMap = function() {
    
    let mapData = {}

    return {
        put: function(key, value) {
            mapData[key] = value;
        },
        keys: function() {
            return Object.keys(mapData);
        },
        contains: function(key) {
            return (key in mapData);
        },
        get: function(key) {
            return mapData[key];
        },
        clear: function() {
           for(let key in mapData){
            delete mapData[key];
           }
        }
    };
};