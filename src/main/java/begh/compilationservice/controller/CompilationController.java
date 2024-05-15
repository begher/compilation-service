package begh.compilationservice.controller;

import begh.compilationservice.model.dto.AmountByCategoryDto;
import begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto;
import begh.compilationservice.model.dto.WeeklyDebitCreditByDateAndTypeDto;
import begh.compilationservice.service.CompilationService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilation")
public class CompilationController {
    private final CompilationService service;

    @GetMapping("/daily")
    Page<DebitCreditByDateAndTypeDto> getCreditDebitByDateAndType(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("accountType") String accountType,
            @PageableDefault(page = 0, size = 100, sort = "voucherDate", direction = Sort.Direction.ASC) @Parameter(hidden = true) Pageable pageable
    ){
        return service.getCreditDebitByDateAndType(startDate, endDate, accountType, pageable);
    }

    @GetMapping("/categoriesbalance")
    ResponseEntity<List<AmountByCategoryDto>> getAmountByCategory(){
        return ResponseEntity.ok(service.getAmountByCategory());
    }

    @GetMapping("/accountbalances")
    ResponseEntity<List<AmountByCategoryDto>> getAccountBalances(
            @RequestParam String type
    ){
        return ResponseEntity.ok(service.getAccountBalances(type));
    }

//    @GetMapping("/weekly")
//    Page<WeeklyDebitCreditByDateAndTypeDto> getWeeklyCreditDebitByDateAndType(
//            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
//            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
//            @RequestParam("accountType") String accountType,
//            @PageableDefault(page = 0, size = 100, sort = "voucherDate", direction = Sort.Direction.ASC) @Parameter(hidden = true) Pageable pageable
//    ){
//        return service.getWeeklyCreditDebitByDateAndType(startDate, endDate, accountType, pageable);
//    }

}
