<?php
namespace Deployer;

require 'recipe/laravel.php';

// Project name
set('application', 'laravel_scafford');

// Project repository
set('repository', 'https://gitee.com/waterloocode/laravel-scafford.git');

// [Optional] Allocate tty for git clone. Default value is false.
if (strtoupper(substr(PHP_OS, 0, 3)) === 'WIN') {
    set('git_tty', false);
    set('ssh_multiplexing', false);
}

// Shared files/dirs between deploys 
add('shared_files', []);
add('shared_dirs', ["storage"]);

// Writable dirs by web server 
add('writable_dirs', []);
set('allow_anonymous_stats', false);

// Hosts
//
//host('project.com')
//    ->set('deploy_path', '~/{{application}}');
host('101.132.74.191')
    ->user("root")
    ->identityFile("/c/Users/admin/Desktop/config/aliyun-private-key/ali-long.pem")
    ->set('deploy_path', '~/{{application}}');
// Tasks
task('test', function () {
    run('echo 123;');
    run('echo 321;');
});

task('build', function () {
    run('cd {{release_path}}');
});

// [Optional] if deploy fails automatically unlock.
after('deploy:failed', 'deploy:unlock');

// Migrate database before symlink new release.

before('deploy:symlink', 'artisan:migrate');

