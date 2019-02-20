<?php
$api = app('Dingo\Api\Routing\Router');
$api->group([
                'version' => "v1"
            ], function (\Dingo\Api\Routing\Router $api) {
    $api->get("test", function () {

    });
});

