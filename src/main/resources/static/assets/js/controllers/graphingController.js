app.controller("graphingController", ["$q", "$scope", "wattageService", "$filter",
            function ($q, $scope, wattageService, $filter) {
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
            //$scope.timestamps = $scope.formatTimestamps(result.data.timeList);
            $scope.timestamps = $scope.formatTimestamps(result.data.timeList);
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

    $scope.formatTimestamps = function (timestamps) {
        // I would use a forEach loop, but it turns out they are quite a bit slower in Javascript.
        var formatted = [];
        for (var i = 0; i < timestamps.length; i++) {
            formatted.push($filter("date")(new Date(timestamps[i]), "dd.MM.yy hh:mm"));
        }
        return formatted;
    };
}]);
