package com.abolkog.springboot.tut.todos;

import com.abolkog.springboot.tut.BaseController;
import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.model.entity.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 09/09/2018 10:08 AM.
 */
@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController extends BaseController{

    @Autowired
    private TodoService todoService;


    /**
     * ResponseEntity :: using this class to return right-response-code like [200, 403, 404, 400, 500]
     */
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Todo>> getAllTodos() {
        
        // Get userName:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        System.out.println("login userName: " + appUser.getName());
        //
        List<Todo> result =  todoService.findByUser(getCurrentUser().getId() + "");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @PathVariable :: get variable from request-url
     */
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable  int id) {///// houssam
        
        Todo result =  todoService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    /**
     * @RequestBody :: get variable from request-body
     * @Valid :: check all beans using @NotNull, @Size
     */
    @PostMapping(value = {"", "/"})
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo todo) {
        
        todo.setUserId(getCurrentUser().getId() + "");
        Todo todoResult = todoService.save(todo);
        return new ResponseEntity<>(todoResult, HttpStatus.CREATED);
    }

    /**
     * @PathVariable :: get variable from request-url
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {/// houssam

        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
