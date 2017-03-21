app.controller("graphingController", ["$q", "$scope", "wattageService", function ($q, $scope, wattageService) {
    $scope.loading = false;
    $scope.timestamps = [];
    $scope.wattages = [[]];
    $scope.error = false;
    $scope.result = false;
    $scope.panelSettings = {
        inclination: "",
        angleRelativeToMedians: "",
        latitude: "",
        longitude: ""
    };
    $scope.colors = ["#18BC9C"];

    $scope.calculate = function (panelSettings) {
        $scope.loading = true;
        $scope.error = false;
        $scope.hasResult = false;
        wattageService.getWattage(panelSettings).then(function (result) {
            $scope.timestamps = result.data.timeList;
            $scope.wattages = [result.data.solarPowerList];
            $scope.hasResult = true;
            $scope.loading = false;
        }).catch(function (wattage) {
            $scope.timestamps = [];
            $scope.wattages = [[]];
            $scope.error = true;
            $scope.loading = false;
        });
    };
}]);
