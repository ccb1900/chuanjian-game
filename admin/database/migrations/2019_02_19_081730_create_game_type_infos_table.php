<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateGameTypeInfosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('game_type_infos', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name')->comment('类型名称棋牌类等');
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
        Schema::dropIfExists('game_type_infos');
    }
}
