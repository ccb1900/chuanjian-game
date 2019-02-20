<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name')->unique()->comment('账号名称');
            $table->string('password')->comment('密码');
            $table->tinyInteger('gender')->default(1)->comment('1男2女');
            $table->tinyInteger('is_allow_login')->default(1)->comment('1允许2禁止');
            $table->timestamp('last_login_at')->nullable()->comment('上次登录时间');
            $table->integer('register_ip')->nullable()->comment('注册ip');
            $table->integer('last_login_ip')->nullable()->comment('最后登录ip');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('users');
    }
}
