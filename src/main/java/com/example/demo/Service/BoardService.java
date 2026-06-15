package com.example.demo.Service;

import com.example.demo.Model.Board;
import com.example.demo.Repository.BoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private int idCount = 0;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Integer id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(Board board) {
        idCount++;
        board.setId(idCount);
        return boardRepository.save(board);
    }

    public Board updateBoard(Integer id, Board updateData) {
        Board board = boardRepository.findById(id);
        if (board != null) {
            board.setName(updateData.getName());
            board.setDescription(updateData.getDescription());
            return boardRepository.save(board);
        }
        return null;
    }

    public boolean deleteBoard(Integer id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
