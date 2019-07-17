package club.yiduo.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract  class AbstractCrudController <S extends IService<T>,T>{

 protected final S service;

 protected AbstractCrudController(S service) {
  this.service = service;
 }

 @GetMapping("/search")
 public IPage<T> search(IPage<T> page, QueryWrapper<T> filtering) {
  return service.page(page, filtering);
 }

 @GetMapping("/list")
 public List<T> listAll(QueryWrapper<T> filtering) {
  return service.list(filtering);
 }

 @GetMapping("/{id}")
 public T get(@PathVariable Integer id) {
  return service.getById(id);
 }

 @PostMapping
 public void saveOrUpdate(@RequestBody T t) {
  service.saveOrUpdate(t);
 }

 @DeleteMapping("/{id}")
 public void delete(@PathVariable Integer id) {
  service.removeById(id);
 }
}
