

		// TodoServiceImple todoService = new TodoServiceImple();

		/*
		 * TodoDto aa1 = new TodoDto(-1L, "daniel", "bb", "2019-09-29 12:30:00", false);
		 * TodoDto aa2 = new TodoDto(-1L, "daniel", "cc", "2019-09-29 12:30:00", false);
		 * 
		 * List<TodoDto> todoDtoList = new ArrayList<TodoDto>(); todoDtoList.add(aa1);
		 * todoDtoList.add(aa2);
		 * 
		 * todoService.addTodoList(todoDtoList);
		 */

		  // batch update 방법 1
		  /*
            result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    TodoDto todo = todoDtoList.get(i);
                    ps.setString(1, todo.getUser());
                    ps.setString(2, todo.getDesc());
                    ps.setString(3, todo.getTargetDate());
                    ps.setBoolean(4,todo.isDone());

                    ps.getGeneratedKeys()
                }

                @Override
                public int getBatchSize() {
                    return todoDtoList.size();
                }
            });
            */
        # 2. Gson 사용법 (json->Object 변환)
        String ss =  "[{\"id\": 1, \"user\": \"daniel\", \"desc\": \"메일\", \"targetDate\": \"2019-09-27 14:30:00\",\"done\": false}," +
		"{\"id\": 3,\"user\": \"daniel\",\"desc\": \"교육\",\"targetDate\": \"2019-10-17 13:30:00\",\"done\": false}]";

        # 방법1 
        Gson gson = new Gson();
		TodoDto[] array = gson.fromJson(ss, TodoDto[].class); 
        List<TodoDto> todos = Arrays.asList(array); 
        
        # 방법2 
        List<TodoDto> list2 = gson.fromJson(ss, new TypeToken<List<TodoDto>>(){}.getType());