package service;

import dao.MovieDAO;

public class DeleteMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Nhận ID phim cần xóa, kiểm tra...
    public boolean deleteMovie(String id) {
        // 1. Kiểm tra đầu vào: Tránh trường hợp ID bị null hoặc rỗng
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        // --- ĐÃ ĐÓNG BĂNG LOGIC GỌI DAO CHỜ TEAM DAO LÀM XONG ---
        // Khi nào file MovieDAO có hàm delete thì chỉ việc mở comment dòng dưới:
        // return movieDAO.delete(id); 
        
        // 2. TẠM THỜI ÉP TRẢ VỀ TRUE ĐỂ TEST LUỒNG GIAO DIỆN (Test xong tính sau)
        return true; 
    }
}