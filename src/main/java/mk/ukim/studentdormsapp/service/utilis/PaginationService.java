package mk.ukim.studentdormsapp.service.utilis;

import lombok.Getter;

import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    public PaginationData calculatePagination(int page, int size, int totalPages) {
        int maxPages = 10;

        page = Math.max(1, Math.min(page, totalPages));
        int startPage = Math.max(1, page - maxPages / 2);
        int endPage = Math.min(totalPages, startPage + maxPages - 1);

        if (endPage - startPage + 1 < maxPages) {
            startPage = Math.max(1, endPage - maxPages);
        }

        return new PaginationData(page, startPage, endPage, totalPages);
    }

    public static class PaginationData {
        private final int currentPage;
        private final int startPage;
        private final int endPage;
        private final int totalPages;

        public PaginationData(int currentPage, int startPage, int endPage, int totalPages) {
            this.currentPage = currentPage;
            this.startPage = startPage;
            this.endPage = endPage;
            this.totalPages = totalPages;
        }


        public int getCurrentPage() { return currentPage; }
        public int getStartPage() { return startPage; }
        public int getEndPage() { return endPage; }
        public int getTotalPages() { return totalPages; }
    }
}