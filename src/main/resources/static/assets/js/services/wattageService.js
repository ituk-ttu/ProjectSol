app.factory("wattageService", ["$q", "$http", function ($q, $http) {

    var getWattage = function (panelSettings) {
        var deferred = $q.defer();
        $http.post(apiBase + "/calculate", panelSettings).then(
            function (response) {
                deferred.resolve(response);
            }).catch(function () {
                deferred.reject();
            });
        return deferred.promise;
    };

    return {
        getWattage: function (panelSettings) {
            return getWattage(panelSettings);
        }
    }
}]);