cordova.define("cordova-plugin-nativecache.NativeCache", function(require, exports, module) {
function NativeCache() {
}

NativeCache.prototype.put = function (options, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "NativeCache", "put", [options]);
};

NativeCache.prototype.get = function (options, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "NativeCache", "get", [options]);
};

NativeCache.prototype.clear = function (options,successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "NativeCache", "clear", [options]);
};

NativeCache.prototype.remove = function (options, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "NativeCache", "delete", [options]);
};

NativeCache.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.nativecache = new NativeCache();

  return window.plugins.nativecache;
};

cordova.addConstructor(NativeCache.install);

});
