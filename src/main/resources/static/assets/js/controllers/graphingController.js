app.controller("graphingController", ["$q", "$scope", "wattageService", function ($q, $scope, wattageService) {
    $scope.loading = false;
    $scope.result = null;
    $scope.error = false;
    $scope.panelSettings = {
        inclination: "",
        angleRelativeToMedians: "",
        latitude: "",
        longitude: ""
    };


    $scope.calculate = function (panelSettings) {
        $scope.loading = true;
        $scope.error = false;
        wattageService.getWattage(panelSettings).then(function (wattage) {
            $scope.result = wattage;
            $scope.loading = false;
        }).catch(function (wattage) {
            $scope.error = true;
            $scope.loading = false;
        });
    }
}]);
