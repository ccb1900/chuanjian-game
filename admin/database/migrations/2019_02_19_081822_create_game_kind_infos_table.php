<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateGameKindInfosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('game_kind_infos', function (Blueprint $table) {
            $table->increments('id');
            $table->unsignedInteger('type_id');
            $table->string('name')->comment('名称：比如德州扑克，穿箭等');
            $table->string('process_name')->default('')->comment('客户端进程名称');
            $table->string('process_max_version')->default('1')->comment('客户端进程最低版本');
            $table->tinyInteger('enable')->default(1)->comment('1显示，控制大厅是否显示');
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
        Schema::dropIfExists('game_kind_infos');
    }
}
